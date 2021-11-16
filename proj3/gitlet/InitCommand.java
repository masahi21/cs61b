package gitlet;

/**
 * @author Matthew Sahim
 */
public class InitCommand implements Command {

    /**
     * Run the command.
     */
    @Override
    public void run(Repository repo, String[] args) {
        repo.init();
    }

    /**
     * Check if requires repo.
     */
    @Override
    public boolean requiresRepo() {
        return false;
    }

    /**
     * Check the operands.
     */
    @Override
    public boolean checkOperands(String[] args) {
        return args.length == 0;
    }

}
