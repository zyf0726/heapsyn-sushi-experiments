package forHeapSyn.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class LeftistHeapLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/leftistheap/LeftistHeap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/leftistheap/LeftistHeap.*");
		super.configureSushiScope(p, "kiasan/leftistheap/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/leftist.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("kiasan.leftistheap.LeftistHeap");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "kiasan/leftistheap/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("kiasan/leftist.jbse"));
		return p;
	}

	public static void main(String[] args) {
		LeftistHeapLauncher launcher = new LeftistHeapLauncher();
		launcher.startSushi();
	}
	
}
