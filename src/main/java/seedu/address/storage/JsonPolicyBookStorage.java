package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyPolicyBook;

/**
 * A class to access PolicyBook data stored as a json file on the hard disk.
 */
public class JsonPolicyBookStorage implements PolicyBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPolicyBookStorage.class);

    private Path filePath;

    public JsonPolicyBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPolicyBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPolicyBook> readPolicyBook() throws DataLoadingException {
        return readPolicyBook(filePath);
    }

    /**
     * Similar to {@link #readPolicyBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyPolicyBook> readPolicyBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializablePolicyBook> jsonPolicyBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePolicyBook.class);
        if (!jsonPolicyBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPolicyBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void savePolicyBook(ReadOnlyPolicyBook policyBook) throws IOException {
        savePolicyBook(policyBook, filePath);
    }

    /**
     * Similar to {@link #savePolicyBook(ReadOnlyPolicyBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePolicyBook(ReadOnlyPolicyBook policyBook, Path filePath) throws IOException {
        requireNonNull(policyBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePolicyBook(policyBook), filePath);
    }
}
