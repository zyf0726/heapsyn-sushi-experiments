package forHeapSyn.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class TreeMapLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/redblacktree/TreeMap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/redblacktree/TreeMap.*");
		super.configureSushiScope(p, "kiasan/redblacktree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/treemap.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("kiasan.redblacktree.TreeMap");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> !name.equals("modCount"));
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "kiasan/redblacktree/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("kiasan/treemap.jbse"));
		return p;
	}

	public static void main(String[] args) {
		TreeMapLauncher launcher = new TreeMapLauncher();
		launcher.startSushi();
	}
	
}
