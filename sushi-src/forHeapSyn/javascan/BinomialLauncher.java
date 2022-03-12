package forHeapSyn.javascan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class BinomialLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("javascan/binomial/BinomialHeap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("javascan/binomial/BinomialHeap.*");
		super.configureSushiScope(p, "javascan/binomial/");
		p.setHEXFiles(SETTINGS_PATH.resolve("javascan/binomial.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("javascan.binomial.BinomialHeap");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "javascan/binomial/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("javascan/binomial.jbse"));
		return p;
	}

	public static void main(String[] args) {
		BinomialLauncher launcher = new BinomialLauncher();
		launcher.startSushi();
	}
	
}
