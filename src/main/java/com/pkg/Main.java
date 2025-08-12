package com.pkg;

import com.pkg.pm.PackageManager;
import com.pkg.pm.PackageManagerCommand;
import com.pkg.pm.PackageManagerFactory;

public class Main {
    private static final String programName = "pkg";

    private static void run(PackageManagerCommand command, String[] packages) throws Exception {
        var pmFactory = new PackageManagerFactory();
        PackageManager pm = pmFactory.getPackageManager();
        int exitCode = pm.executeCommand(command, packages);
        System.exit(exitCode);
    }

    private static void printHelp() {
        var builder = new StringBuilder();
        builder.append(String.format("usage: %s [COMMAND] [PACKAGES]\n\n", Main.programName));
        builder.append("commands:\n");
        builder.append("  help        display this help message and exit.\n");
        builder.append("  search      search for packages.\n");
        builder.append("  install     install new packages.\n");
        builder.append("  update      fetch and install package updates.\n");
        builder.append("  remove      remove packages.\n");
        System.out.println(builder.toString());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            Main.printHelp();
            System.exit(1);
        }

        PackageManagerCommand cmd;
        try {
            cmd = PackageManagerCommand.valueOf(args[0]);
        } catch (Exception ex) {
            System.err.println("invalid command: " + args[0]);
            Main.printHelp();
            System.exit(1);
            return;
        }

        if (cmd == PackageManagerCommand.help) {
            Main.printHelp();
            System.exit(0);
            return;
        }

        String[] packages = {};
        if (args.length > 1) {
            packages = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                packages[i - 1] = args[i];
            }
        }

        try {
            Main.run(cmd, packages);
        } catch (Exception ex) {
            System.err.println("erorr: " + ex.getMessage());
            System.exit(1);
        }
    }
}
