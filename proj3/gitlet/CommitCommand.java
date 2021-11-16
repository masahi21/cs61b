package gitlet;

import java.util.HashMap;

/**
 * @author Matthew Sahim
 */

public class CommitCommand implements Command {

    /** Run the command. */
    @Override
    public void run(Repository repo, String[] args) {
        String message = args[0];

        Index index = repo.index();
        HashMap<String, String> blobs = index.blobsFromStage();

        repo.addCommitAtHead(message, blobs);
    }

    /** Check if requires repo. */
    @Override
    public boolean requiresRepo() {
        return true;
    }

    /** Check the operands. */
    @Override
    public boolean checkOperands(String[] args) {
        return args.length == 1;
    }

}
