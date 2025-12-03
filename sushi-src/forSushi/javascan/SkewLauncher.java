package forSushi.javascan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

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
	
	public static void main(String[] args) {
		SkewLauncher launcher = new SkewLauncher();
		launcher.startSushi("[forSushi]javascan.SkewHeap.txt");
	}
	
}
