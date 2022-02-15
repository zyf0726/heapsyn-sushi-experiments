package forHeapSyn.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class StackLiLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/stack/StackLi");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/stack/StackLi.*");
		super.configureSushiScope(p, "kiasan/stack/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/stackli.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("kiasan.stack.StackLi");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "kiasan/stack/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("kiasan/stackli.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		StackLiLauncher launcher = new StackLiLauncher();
		launcher.startSushi();
	}

}
