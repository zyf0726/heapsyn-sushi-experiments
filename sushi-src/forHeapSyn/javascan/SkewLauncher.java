package forHeapSyn.javascan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class SkewLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("javascan/skewheap/SkewHeap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("javascan/skewheap/SkewHeap.*");
		super.configureSushiScope(p, "javascan/skewheap/");
		p.setHEXFiles(SETTINGS_PATH.resolve("javascan/skew.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("javascan.skewheap.SkewHeap");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "javascan/skewheap/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("javascan/skew.jbse"));
		return p;
	}

	public static void main(String[] args) {
		SkewLauncher launcher = new SkewLauncher();
		launcher.startSushi("javascan.SkewHeap.txt");
	}
	
}
