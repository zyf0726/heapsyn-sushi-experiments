package forSushi.kiasan.leftistheap;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class LeftistHeapLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/kiasan/leftistheap/LeftistHeap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/kiasan/leftistheap/LeftistHeap.*");
		super.configureScope(p, "forSushi/kiasan/leftistheap/LeftistHeap$LeftistNode");
		super.configureScope(p, "forSushi/kiasan/leftistheap/LeftistHeap");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/kiasan/leftist.jbse"));
	}

	public static void main(String[] args) {
		LeftistHeapLauncher launcher = new LeftistHeapLauncher();
		launcher.startSushi();
	}
	
}
