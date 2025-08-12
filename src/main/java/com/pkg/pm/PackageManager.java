package com.pkg.pm;

import com.pkg.CommandRunner;

public abstract class PackageManager {
    protected CommandRunner commandRunner;

    public PackageManager() {
        commandRunner = new CommandRunner();
    }

    protected abstract int searchPackage(String[] packages) throws Exception;

    protected abstract int installPackages(String[] packages) throws Exception;

    abstract int updatePackages() throws Exception;

    abstract int removePackages(String[] packages) throws Exception;

    public int executeCommand(PackageManagerCommand command, String[] packages) throws Exception {
        switch (command) {
            case PackageManagerCommand.search:
                return searchPackage(packages);

            case PackageManagerCommand.install:
                return installPackages(packages);

            case PackageManagerCommand.update:
                return updatePackages();

            case PackageManagerCommand.remove:
                return removePackages(packages);

            default:
                throw new Exception("invalid command: " + command);
        }
    }

    protected String[] combineArrays(String[] baseCommand, String[] packages) {
        int combinedLength = baseCommand.length + packages.length;
        String[] combinedCommand = new String[combinedLength];

        for (int i = 0; i < baseCommand.length; i++) {
            combinedCommand[i] = baseCommand[i];
        }

        for (int i = 0; i < packages.length; i++) {
            combinedCommand[baseCommand.length + i] = packages[i];
        }

        return combinedCommand;
    }
}
