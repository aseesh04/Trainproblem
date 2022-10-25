package testcase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class Demo11TestCase {

	@Test
	void test() {
		List<String>Train_A=new ArrayList<>();
		String A="TRAIN_A ENGINE BLR AGA BLR HYB ITJ BPL";
		String arrayA[]=A.split(" ");
		Train_A=Arrays.asList(arrayA);
		
		assertEquals(true,Train_A.contains("BLR"));
	}

}
