package common;

import sushi.Level;
import sushi.Main;
import sushi.exceptions.UnhandledInternalException;
import sushi.execution.evosuite.HeapSynParameters;
import sushi.execution.evosuite.RunHeapSyn;
import sushi.execution.jbse.StateTransSpec;

import static common.Settings.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import heapsyn.common.settings.JBSEParameters;

public class BasicLauncher {
	
	private static final int EVOSUITE_BUDGET	= 180;   // 3min
	private static final int JBSE_BUDGET		= 1800;  // 30min
	private static final int MINIMIZER_BUDGET	= 300;   // 5min
	private static final int GLOBAL_BUDGET		= 3600;  // 1hr
	
	private static final int REDUNDANCE			= 1; 
	private static final int PARALLELISM		= 1;
	private static final int NUM_MOSA_TARGETS	= 1;
	
	private static Map<String, Integer> scope$JBSE = new HashMap<>();
	private static Map<String, Integer> scope$HeapSyn = new HashMap<>();
	private static Map<String, Integer> maxSeqLength = new HashMap<>();
	private static Map<String, Integer> maxCount = new HashMap<>();
	private static Map<String, Integer> maxDepth = new HashMap<>();
	
	static {
		/* ========================= sushi ===========================
		 * (1) AvlTree: scope$AvlTree = 1, scope$AvlNode = 5, maxSeqLength = 7
		 * (2) NodeCachingLinkedList: scope$List = 1, scopeForJBSE$Node = 4,
		 *                            scopeForHeap$Node = 6, maxSeqLength = 9
		 * (3) TreeMap: scope$TreeMap = 1, scopeForJBSE$Entry = 5,
		 *              scopeForHeap$Entry = 6, maxSeqLength = 9
		 * (4) LinkedList: scope$List = 1, scope$Entry = 4, scope$Iter = 1,
		 *                 scope$DesIter = 1, maxSeqLength = 5
		 */		
		
		// sushi.avl
		scope$JBSE.put("sushi/avl/AvlNode", 5);
		scope$HeapSyn.put("sushi/avl/AvlNode", 5);
		scope$HeapSyn.put("sushi/avl/AvlTree", 1);
		maxSeqLength.put("sushi/avl/", 7);
		// sushi.ncll
		scope$JBSE.put("sushi/ncll/NodeCachingLinkedList$LinkedListNode", 4);
		scope$HeapSyn.put("sushi/ncll/NodeCachingLinkedList$LinkedListNode", 6);
		scope$HeapSyn.put("sushi/ncll/NodeCachingLinkedList", 1);
		maxSeqLength.put("sushi/ncll/", 9);
		// sushi.treemap
		scope$JBSE.put("sushi/treemap/TreeMap$Entry", 5);
		scope$HeapSyn.put("sushi/treemap/TreeMap$Entry", 6);
		scope$HeapSyn.put("sushi/treemap/TreeMap", 1);
		maxSeqLength.put("sushi/treemap/", 9);
		// sushi.dll
		scope$JBSE.put("sushi/dll/LinkedList$Entry", 4);
		scope$HeapSyn.put("sushi/dll/LinkedList$Entry", 4);
		scope$HeapSyn.put("sushi/dll/LinkedList$ListItr", 1);
		scope$HeapSyn.put("sushi/dll/LinkedList$DescendingIterator", 1);
		scope$HeapSyn.put("sushi/dll/LinkedList", 1);
		maxSeqLength.put("sushi/dll/", 5);
		
		/* ========================== kiasan ==========================
		 * (1) AATree: scope$AATree = 1, scopeForJBSE$AANode = 4,
		 * 	           scopeForHeap$AANode = 5, maxSeqLength = 6
		 * (3) BinTree: scope$BinTree = 1, scope$BinNode = 5, maxSeqLength = 7
		 * (4) LeftistHeap: scope$Heap = 2, scopeForJBSE$Node = 5,
		 *                  scopeForHeap$Node = 7, maxSeqLength = 9
		 * (5) StackLi: scope$Stack = 1, scope$Node = 6, maxSeqLength = 8
		 * (2) AvlTree: scope$AvlTree = 1, scope$AvlNode = 5, maxSeqLength = 7
		 * (6) TreeMap: scope$TreeMap = 1, scopeForJBSE$Entry = 5,
		 *              scopeForHeap$Entry = 6, maxSeqLength = 9
		 */
		
		// kiasan.aatree
		scope$JBSE.put("kiasan/aatree/AATree$AANode", 4);
		scope$HeapSyn.put("kiasan/aatree/AATree$AANode", 5);
		scope$HeapSyn.put("kiasan/aatree/AATree", 1);
		maxSeqLength.put("kiasan/aatree/", 6);
		// kiasan.binsearchtree
		scope$JBSE.put("kiasan/binsearchtree/BinaryNode", 5);
		scope$HeapSyn.put("kiasan/binsearchtree/BinaryNode", 5);
		scope$HeapSyn.put("kiasan/binsearchtree/BinarySearchTree", 1);
		maxSeqLength.put("kiasan/binsearchtree/", 7);
		// kiasan.leftistheap
		scope$JBSE.put("kiasan/leftistheap/LeftistHeap$LeftistNode", 5);
		scope$JBSE.put("kiasan/leftistheap/LeftistHeap", 2);
		scope$HeapSyn.put("kiasan/leftistheap/LeftistHeap$LeftistNode", 7);
		scope$HeapSyn.put("kiasan/leftistheap/LeftistHeap", 2);
		maxSeqLength.put("kiasan/leftistheap/", 9);
		// kiasan.stack
		scope$JBSE.put("kiasan/stack/ListNode", 6);
		scope$HeapSyn.put("kiasan/stack/ListNode", 6);
		scope$HeapSyn.put("kiasan/stack/StackLi", 1);
		maxSeqLength.put("kiasan/stack/", 8);
		// kiasan.avltree
		scope$JBSE.put("kiasan/avltree/AvlNode", 5);
		scope$HeapSyn.put("kiasan/avltree/AvlNode", 5);
		scope$HeapSyn.put("kiasan/avltree/AvlTree", 1);
		maxSeqLength.put("kiasan/avltree/", 7);
		// kiasan.redblacktree
		scope$JBSE.put("kiasan/redblacktree/TreeMap$Entry", 5);
		scope$HeapSyn.put("kiasan/redblacktree/TreeMap$Entry", 6);
		scope$HeapSyn.put("kiasan/redblacktree/TreeMap", 1);
		maxSeqLength.put("kiasan/redblacktree/", 9);
		
		/* =========================== SIR ============================
		 * (1) DoubleLinkedList: scope$List = 1, scope$Entry = 4, scope$Iter = 1,
		 *                       maxSeqLength = 5, maxDepth = 20, maxCount = 100 
		 */
		
		// sir.dll
		scope$JBSE.put("sir/dll/DoubleLinkedList$Entry", 4);
		maxDepth.put("sir/dll/", 20);
		maxCount.put("sir/dll/", 100);
		scope$HeapSyn.put("sir/dll/DoubleLinkedList$Entry", 4);
		scope$HeapSyn.put("sir/dll/DoubleLinkedList$ListItr", 1);
		scope$HeapSyn.put("sir/dll/DoubleLinkedList", 1);
		maxSeqLength.put("sir/dll/", 5);
	}
	
	public void configureSushi(sushi.Options p) {
		// Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setEvosuiteNoDependency(true);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);
		
		// Target
		p.setClassesPath(BIN_PATH_FOR_SUSHI);
		
		// Analysis params
		p.setEvosuiteBudget(EVOSUITE_BUDGET);
		p.setJBSEBudget(JBSE_BUDGET);
		p.setMinimizerBudget(MINIMIZER_BUDGET);
		
		// Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		// Redundance and parallelism
		p.setRedundanceEvosuite(REDUNDANCE);
		p.setParallelismEvosuite(PARALLELISM);
		p.setParallelismJBSE(PARALLELISM);
		p.setNumMOSATargets(NUM_MOSA_TARGETS);
		
		// Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8");
		
		// Logging
		p.setLogLevel(Level.DEBUG);
		
		// Timeout
		p.setGlobalBudget(GLOBAL_BUDGET);
	}
	
	final protected void configureSushiScope(sushi.Options p, String packageName) {
		for (Entry<String, Integer> entry : scope$JBSE.entrySet()) {
			String clsName = entry.getKey();
			int scope = entry.getValue();
			if (clsName.startsWith(packageName)) {
				p.setHeapScope(clsName, scope);
				System.out.print("INFO - experimental setup: set heap scope (sushi.jbse) of class ");
				System.out.println(clsName + " to " + scope);
			}
		}
		if (maxDepth.containsKey(packageName)) {
			int scope = maxDepth.get(packageName);
			p.setDepthScope(scope);
			System.out.print("INFO - experimental setup: set depth scope (sushi.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
		if (maxCount.containsKey(packageName)) {
			int scope = maxCount.get(packageName);
			p.setCountScope(scope);
			System.out.print("INFO - experimental setup: set count scope (sushi.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
	}
	
	final private void configureHeapSyn() {
		heapsyn.common.settings.Options o = HeapSynParameters.heapsynOptions;
		heapsyn.common.settings.JBSEParameters p = HeapSynParameters.jbseParams;
		o.setSolverExecPath(Z3_PATH.toAbsolutePath().toString());
		p.setJBSEClassPath(JBSE_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setJBSESourcePath(JBSE_SRC_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setTargetClassPath(BIN_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setTargetSourcePath(SRC_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setShowOnConsole(true);
	}
	
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		return null;
	}
	
	final protected void configureHeapSynScope(HeapSynParameters p, String packageName) {
		JBSEParameters jbseParams = HeapSynParameters.jbseParams;
		jbseParams.resetAllScope();
		for (Entry<String, Integer> entry : scope$JBSE.entrySet()) {
			String clsName = entry.getKey();
			int scope = entry.getValue();
			if (clsName.startsWith(packageName)) {
				jbseParams.setHeapScope(clsName, scope);
				System.out.print("INFO - experimental setup: set heap scope (heapsyn.jbse) of class ");
				System.out.println(clsName + " to " + scope);
			}
		}
		if (maxDepth.containsKey(packageName)) {
			int scope = maxDepth.get(packageName);
			jbseParams.setDepthScope(scope);
			System.out.print("INFO - experimental setup: set depth scope (heapsyn.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
		if (maxCount.containsKey(packageName)) {
			int scope = maxCount.get(packageName);
			jbseParams.setCountScope(scope);
			System.out.print("INFO - experimental setup: set count scope (heapsyn.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
		for (Entry<String, Integer> entry : scope$HeapSyn.entrySet()) {
			String clsName = entry.getKey();
			int scope = entry.getValue();
			if (clsName.startsWith(packageName)) {
				final Class<?> cls;
				try {
					cls = Class.forName(clsName.replace('/', '.'));
				} catch (ClassNotFoundException e) {
					throw new UnhandledInternalException(e);
				}
				p.setHeapScope(cls, scope);
				System.out.print("INFO - experimental setup: set heap scope (heapsyn) of class ");
				System.out.println(clsName + " to " + scope);
			}
		}
		int maxLength = maxSeqLength.get(packageName);
		p.setMaxSeqLength(maxLength);
		System.out.print("INFO - experimental setup: set max sequence length for package ");
		System.out.println(packageName + " to " + maxLength);
	}
	
	final protected void configureHeapSynHEXFile(Path hexFilePath) {
		HeapSynParameters.jbseParams.setSettingsPath(hexFilePath.toAbsolutePath().toString());
	}
	
	final public void startSushi() {
		final sushi.Options o = new sushi.Options();
		this.configureSushi(o);
		try {
			HeapSynParameters params = this.getHeapSynParameters();
			if (params != null) {
				configureHeapSyn();
				StateTransSpec.setFieldFilter(params.getFieldFilter());
				o.setHeapSynRunner(new RunHeapSyn(params));
			} else {
				StateTransSpec.setFieldFilter(name -> true);
				o.setHeapSynRunner(null);
			}
		} catch (ClassNotFoundException e) {
			throw new UnhandledInternalException(e);
		}
		final Main m = new Main(o);
		final int exitCode = m.start();
		if (exitCode != 0) {
			System.out.println("ERROR - exit with code " + exitCode);
			System.exit(exitCode);
		}
	}

}
