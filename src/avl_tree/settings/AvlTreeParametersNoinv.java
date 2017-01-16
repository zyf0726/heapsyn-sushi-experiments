package avl_tree.settings;

import static common.Settings.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import sushi.configure.Coverage;
import sushi.configure.JBSEParameters;
import sushi.configure.MergerParameters;
import sushi.configure.Options;
import sushi.configure.ParametersModifier;
import sushi.configure.ParseException;
import sushi.logging.Level;

public class AvlTreeParametersNoinv extends ParametersModifier {
	@Override
	public void modify(Options p) {
		//Local configurations
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setZ3Path(Z3_PATH);
		
		//Target 
		p.setClassesPath(BIN_PATH, JBSE_PATH);
		p.setJREPath(JRE_PATH);
		p.setTargetClass("avl_tree/AvlTree");
			
		//Analysis params 
		p.setEvosuiteBudget(60);
		p.setJBSEBudget(3600);
		p.setCoverage(Coverage.BRANCHES);
		p.setLogLevel(Level.INFO);

		//Tmp out directories
		p.setOutDirectory(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		//Parallelism
		p.setRedundanceEvosuite(1);
		p.setParallelismEvosuite(20);
	}
	
	@Override
	public void modify(JBSEParameters p) 
	throws FileNotFoundException, ParseException, IOException {
		loadHEXFile(SETTINGS_PATH + "avl_tree_noinv.jbse", p);
		p.setHeapScope("avl_tree/AvlNode", 5);
	}							 

	@Override
	public void modify(MergerParameters p) {
		p.setBranchesToCover("avl_tree/AvlTree.*");
	}

	@Override
	public void modify(List<String> p) {
		p.add("-Dobject_reuse_probability=0.8");
	}
}
