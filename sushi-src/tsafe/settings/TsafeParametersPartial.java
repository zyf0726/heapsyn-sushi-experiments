package tsafe.settings;

import static common.Settings.BIN_PATH;
import static common.Settings.EVOSUITE_PATH;
import static common.Settings.JAVA8_HOME;
import static common.Settings.JBSE_PATH;
import static common.Settings.OUT_PATH;
import static common.Settings.SETTINGS_PATH;
import static common.Settings.SUSHI_LIB_PATH;
import static common.Settings.TMP_BASE_PATH;
import static common.Settings.Z3_PATH;

import sushi.Coverage;
import sushi.Options;
import sushi.OptionsConfigurator;
import sushi.Rewriter;
import sushi.Level;

public class TsafeParametersPartial implements OptionsConfigurator {
	@Override
	public void configure(Options p) {
		//Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);

		//Target 
		p.setClassesPath(BIN_PATH);
		p.setTargetClass("tsafe/TsafeTrajectorySynthesis");
		
		//Analysis params 
		p.setEvosuiteBudget(240);
		p.setJBSEBudget(3600);
		p.setMinimizerBudget(300);
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("tsafe/TsafeTrajectorySynthesis.*");
		p.setHeapScope("common/LinkedList$Entry", 3);
		p.setHEXFiles(SETTINGS_PATH.resolve("linked_list.jbse"), SETTINGS_PATH.resolve("tsafe_partial.jbse"));
		p.setDoSignAnalysis(true);
		p.setRewriters(Rewriter.ABS_SUM, Rewriter.POLYNOMIALS, Rewriter.SIN_COS, Rewriter.SQRT);

		//Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);

		//Redundance and parallelism
		p.setParallelismEvosuite(20);
		
		//Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8 -Delite=5");

		//Logging
		p.setLogLevel(Level.INFO);
		
		//Timeout
		p.setGlobalBudget(7200);
	}
}
