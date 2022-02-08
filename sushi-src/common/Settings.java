package common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {
	//BEGIN TO PATCH
	//Settings for Docker
	//public static final Path SUSHI_WORKSPACE       = Paths.get("/root", "sushi");
	//public static final Path EXPERIMENTS_WORKSPACE = Paths.get("/root", "sushi-experiments");
	//public static final Path Z3_PATH               = Paths.get("/usr", "bin", "z3");
	//public static final Path JAVA8_HOME            = Paths.get("/usr", "lib", "jvm", "java-8-openjdk-amd64");

	//zyf0726's local settings
	public static final Path SUSHI_WORKSPACE       = Paths.get("D:", "Workspace", "eclipse-workspaces", "HeapSyn-Experiments", "git", "sushi");
	public static final Path EXPERIMENTS_WORKSPACE = Paths.get("D:", "Workspace", "eclipse-workspaces", "HeapSyn-Experiments", "git", "heapsyn-sushi-experiments");
	public static final Path Z3_PATH               = Paths.get("D:", "Tools", "z3-4.8.10-x64-win", "bin", "z3.exe");
	public static final Path JAVA8_HOME            = Paths.get("C:", "Program Files", "Java", "jdk1.8.0_231");
	//END TO PATCH

	public static final Path JBSE_PATH      = SUSHI_WORKSPACE.resolve(Paths.get("jbse", "build", "classes", "java", "main"));
	public static final Path SUSHI_LIB_PATH = SUSHI_WORKSPACE.resolve(Paths.get("runtime", "build", "classes", "java", "main"));
	public static final Path EVOSUITE_PATH  = SUSHI_WORKSPACE.resolve(Paths.get("libs", "evosuite-shaded-1.1.1-SNAPSHOT.jar"));
	public static final Path BIN_PATH       = EXPERIMENTS_WORKSPACE.resolve(Paths.get("bin"));
	public static final Path GUAVA_PATH     = EXPERIMENTS_WORKSPACE.resolve(Paths.get("libs", "ganttproject-guava.jar"));
	public static final Path TMP_BASE_PATH  = EXPERIMENTS_WORKSPACE.resolve(Paths.get("sushi-out"));
	public static final Path OUT_PATH       = EXPERIMENTS_WORKSPACE.resolve(Paths.get("sushi-test"));
	public static final Path SETTINGS_PATH  = EXPERIMENTS_WORKSPACE.resolve(Paths.get("settings"));
}
