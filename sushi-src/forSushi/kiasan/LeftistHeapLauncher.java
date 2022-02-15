package forSushi.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

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

	public static void main(String[] args) {
		LeftistHeapLauncher launcher = new LeftistHeapLauncher();
		launcher.startSushi();
	}
	
}
