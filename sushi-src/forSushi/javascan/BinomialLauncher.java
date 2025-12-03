package forSushi.javascan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

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
	
	public static void main(String[] args) {
		BinomialLauncher launcher = new BinomialLauncher();
		launcher.startSushi("[forSushi]javascan.BinomHeap.txt");
	}
	
}
