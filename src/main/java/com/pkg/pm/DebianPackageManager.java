package com.pkg.pm;

import com.pkg.CommandLineColor;

public class DebianPackageManager extends PackageManager {
  @Override
  protected int searchPackage(String[] packages) throws Exception {
    String packageName = packages[0];
    if (packages.length > 1) {
      System.out.printf("%swarning: can only search for one package at a time.%s\n", CommandLineColor.YELLOW,
          CommandLineColor.RESET);
    }

    return commandRunner.run("apt-cache", "search", packageName);
  }

  @Override
  protected int installPackages(String[] packages) throws Exception {
    String[] baseCmd = { "sudo", "apt-get", "install", "-y" };
    String[] combinedCommand = combineArrays(baseCmd, packages);
    return commandRunner.run(combinedCommand);
  }

  @Override
  protected int updatePackages() throws Exception {
    int code = commandRunner.run("sudo", "apt-get", "update", "-y");
    if (code != 0) {
      return code;
    }

    return commandRunner.run("sudo", "apt-get", "upgrade", "-y");
  }

  @Override
  protected int removePackages(String[] packages) throws Exception {
    String[] baseCmd = { "sudo", "apt-get", "remove", "-y", "--purge" };
    String[] combinedCmd = combineArrays(baseCmd, packages);
    int code = commandRunner.run(combinedCmd);
    if (code != 0) {
      return code;
    }

    return commandRunner.run("sudo", "apt-get", "autoremove", "-y");
  }
}
