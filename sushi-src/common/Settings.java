package common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {

	public static final Path SUSHI_WORKSPACE       = Paths.get("D:", "Code", "eclipse-workspaces", "HeapSyn-Experiments", "git", "sushi");
	public static final Path EXPERIMENTS_WORKSPACE = Paths.get("D:", "Code", "eclipse-workspaces", "HeapSyn-Experiments", "git", "heapsyn-sushi-experiments");
	public static final Path Z3_PATH               = Paths.get("D:", "Tools", "z3-4.8.10-x64-win", "bin", "z3.exe");
	public static final Path JAVA8_HOME            = Paths.get("C:", "Program Files", "Java", "jdk1.8.0_271");

	public static final Path JBSE_PATH      = SUSHI_WORKSPACE.resolve(Paths.get("jbse", "build", "classes", "java", "main"));
	public static final Path SUSHI_LIB_PATH = SUSHI_WORKSPACE.resolve(Paths.get("runtime", "build", "classes", "java", "main"));
	public static final Path EVOSUITE_PATH  = SUSHI_WORKSPACE.resolve(Paths.get("libs", "evosuite-shaded-1.1.1-SNAPSHOT.jar"));
	public static final Path BIN_PATH_FOR_SUSHI	= EXPERIMENTS_WORKSPACE.resolve(Paths.get("benchmarks-copied-for-sushi", "bin"));
	public static final Path SRC_PATH_FOR_SUSHI	= EXPERIMENTS_WORKSPACE.resolve(Paths.get("benchmarks-copied-for-sushi", "src"));
	public static final Path TMP_BASE_PATH  = EXPERIMENTS_WORKSPACE.resolve(Paths.get("sushi-out"));
	public static final Path OUT_PATH       = EXPERIMENTS_WORKSPACE.resolve(Paths.get("sushi-test"));
	public static final Path SETTINGS_PATH  = EXPERIMENTS_WORKSPACE.resolve(Paths.get("HEXsettings"));
	
	public static final Path JBSE_PATH_FOR_HEAPSYN		= SUSHI_WORKSPACE.resolve(Paths.get("libs", "HeapSyn-0.10.0-SNAPSHOT-all.jar"));
	public static final Path JBSE_SRC_PATH_FOR_HEAPSYN	= SRC_PATH_FOR_SUSHI;  // useless
	public static final Path BIN_PATH_FOR_HEAPSYN		= EXPERIMENTS_WORKSPACE.resolve(Paths.get("bin"));
	public static final Path SRC_PATH_FOR_HEAPSYN		= EXPERIMENTS_WORKSPACE.resolve(Paths.get("src"));

}
