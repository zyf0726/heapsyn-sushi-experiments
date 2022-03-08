package forHeapSyn.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class TreeMapLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/treemap/TreeMap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/treemap/TreeMap(?!.*HEXTriggers.*$).*");
		super.configureSushiScope(p, "sushi/treemap/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/treemap.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("sushi.treemap.TreeMap");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name ->
				(!name.startsWith("_") || name.equals("_blackHeight") || name.equals("_owner"))
				&& !name.equals("modCount"));
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "sushi/treemap/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("sushi/treemap.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		TreeMapLauncher launcher = new TreeMapLauncher();
		launcher.startSushi();
	}

}
