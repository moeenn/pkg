package com.pkg.pm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PackageManagerFactory {
    public PackageManager getPackageManager() throws Exception {
        String distroName = this.getDistroName();
        switch (distroName) {
            case "debian", "\"ubuntu debian\"":
                return new DebianPackageManager();

            default:
                throw new Exception(String.format("Package manager for %s has not been integrated yet.", distroName));
        }
    }

    public String getDistroName() throws Exception {
        String filePath = "/etc/os-release";

        List<String> lines = Files.readAllLines((Paths.get(filePath)));
        for (String line : lines) {
            if (line.startsWith("ID_LIKE=")) {
                String[] pieces = line.split("=");
                return pieces[1].trim();
            }
        }

        throw new Exception("failed to identify distro name");
    }
}
