package com.smart.basic;



public class SequenceNumber {
	
	private static ThreadLocal<Integer> seqnum= new ThreadLocal<Integer>(){
			public Integer initialValue(){
				return 0;
			}
	};
	public int getNextNum(){
		seqnum.set(seqnum.get()+1);
		return seqnum.get();
		
	}
	
	public static void main(String[] args) {
		SequenceNumber sn= new SequenceNumber();
		TestClient tc1 =new TestClient(sn);
		TestClient tc2 =new TestClient(sn);
		TestClient tc3 =new TestClient(sn);
		tc1.start();
		tc2.start();
		tc3.start();
	}
	private static class TestClient extends Thread{
		private SequenceNumber sn;

		public TestClient(SequenceNumber sn) {
			super();
			this.sn = sn;
		}
		public void run(){
			for(int i=0;i<3;i++){
				System.out.println("Thread ["+Thread.currentThread().getName()+" ] sn:"+sn.getNextNum());
			}
		}
	}
}
