package forHeapSyn.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class AvlLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/avl/AvlTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/avl/AvlTree.*");
		super.configureSushiScope(p, "sushi/avl/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/avltree.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("sushi.avl.AvlTree");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> !name.startsWith("_"));
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "sushi/avl/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("sushi/avltree.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		AvlLauncher launcher = new AvlLauncher();
		launcher.startSushi();
	}

}
