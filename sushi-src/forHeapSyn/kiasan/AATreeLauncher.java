package forHeapSyn.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class AATreeLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/aatree/AATree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/aatree/AATree.*");
		super.configureSushiScope(p, "kiasan/aatree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/aatree.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("kiasan.aatree.AATree");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> !name.equals("deletedNode") && !name.equals("lastNode"));
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "kiasan/aatree/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("kiasan/aatree.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		AATreeLauncher launcher = new AATreeLauncher();
		launcher.startSushi();
	}

}
