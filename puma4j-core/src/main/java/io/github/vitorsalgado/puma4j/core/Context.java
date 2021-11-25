package io.github.vitorsalgado.puma4j.core;

public class Context {

  private final String basePath;

  public Context(final String basePath) {
    this.basePath = fixBasePath(basePath);
  }

  public static Context defaultContext() {
    return new Context("");
  }

  public String getBasePath() {
    return basePath;
  }

  private static String fixBasePath(final String path) {
    if (path.startsWith("/")) {
      return path;
    }

    return "/" + path;
  }
}
