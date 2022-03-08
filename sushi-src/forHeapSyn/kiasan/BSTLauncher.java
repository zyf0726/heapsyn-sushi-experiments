package forHeapSyn.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class BSTLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/binsearchtree/BinarySearchTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/binsearchtree/BinarySearchTree.*");
		super.configureSushiScope(p, "kiasan/binsearchtree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/bst.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("kiasan.binsearchtree.BinarySearchTree");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "kiasan/binsearchtree/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("kiasan/bst.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		BSTLauncher launcher = new BSTLauncher();
		launcher.startSushi();
	}

}
