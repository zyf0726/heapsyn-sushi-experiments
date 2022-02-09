package common;

import sushi.Level;
import sushi.Main;
import sushi.Options;
import sushi.OptionsConfigurator;

import static common.Settings.*;

import java.util.HashMap;
import java.util.Map;

public class BasicLauncher implements OptionsConfigurator {
	
	private static final int EVOSUITE_BUDGET	= 180;   // 3min
	private static final int JBSE_BUDGET		= 1800;  // 30min
	private static final int MINIMIZER_BUDGET	= 300;   // 5min
	private static final int GLOBAL_BUDGET		= 3600;  // 1hr
	
	private static final int REDUNDANCE			= 1; 
	private static final int PARALLELISM		= 1;
	private static final int NUM_MOSA_TARGETS	= 1;
	
	private static Map<String, Integer> scope$JBSE = new HashMap<>();
	
	static {
		scope$JBSE = new HashMap<>();
		// sushi.avl
		scope$JBSE.put("forSushi/sushi/avl/AvlNode", 5);
		// sushi.dll
		scope$JBSE.put("forSushi/sushi/dll/LinkedList$Entry", 4);
	}
	
	@Override
	public void configure(Options p) {
		// Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setEvosuiteNoDependency(true);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);
		
		// Target
		p.setClassesPath(BIN_PATH);
		
		// Analysis params
		p.setEvosuiteBudget(EVOSUITE_BUDGET);
		p.setJBSEBudget(JBSE_BUDGET);
		p.setMinimizerBudget(MINIMIZER_BUDGET);
		
		// Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		// Redundance and parallelism
		p.setRedundanceEvosuite(REDUNDANCE);
		p.setParallelismEvosuite(PARALLELISM);
		p.setParallelismJBSE(PARALLELISM);
		p.setNumMOSATargets(NUM_MOSA_TARGETS);
		
		// Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8");
		
		// Logging
		p.setLogLevel(Level.DEBUG);
		
		// Timeout
		p.setGlobalBudget(GLOBAL_BUDGET);
	}
	
	final public void configureScope(Options p, String clsName) {
		int scope = scope$JBSE.get(clsName);
		p.setHeapScope(clsName, scope);
	}
	
	final public int startSushi() {
		final Options o = new Options();
		this.configure(o);
		final Main m = new Main(o);
		return m.start();
	}

}
