package forSushi.kiasan.avltree;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class AvlLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/kiasan/avltree/AvlTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/kiasan/avltree/AvlTree.*");
		super.configureScope(p, "forSushi/kiasan/avltree/AvlNode");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/kiasan/avltree.jbse"));
	}
	
	public static void main(String[] args) {
		AvlLauncher launcher = new AvlLauncher();
		launcher.startSushi();
	}

}
