package forHeapSyn.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class AvlLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/avltree/AvlTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/avltree/AvlTree.*");
		super.configureSushiScope(p, "kiasan/avltree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/avltree.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("kiasan.avltree.AvlTree");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "kiasan/avltree/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("kiasan/avltree.jbse"));
		return p;
	}	
	
	public static void main(String[] args) {
		AvlLauncher launcher = new AvlLauncher();
		launcher.startSushi();
	}

}
