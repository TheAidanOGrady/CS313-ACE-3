package vmm.manager;

/**
 * This class represents our physical memory, and is essentially a 2d array of
 * bytes. The memory is able to be referred to and updated throughout the
 * execution of the program.
 * 
 * @author Aidan O'Grady
 * @version 1.3
 * @since 0.2
 *
 */
public class PhysicalMemory {
	
	/**
	 * The memory's data is stored in this array.
	 */
	private byte[][] physicalMemory;
	
	/**
	 * The next available frame in memory.
	 */
	private int nextIndex;
	
	/**
	 * The number of frames within memory.
	 */
	private int size;
	
	/**
	 * The size of a single frame
	 */
	private int frameSize;
	
	/**
	 * Constructs new physical memory with a given number of frames.
	 * 
	 * @param noOfFrames - the number of frames to be included.
	 */
	public PhysicalMemory(int size, int frameSize){
		this.size = size;
		this.frameSize = frameSize;
		
		nextIndex = 0;
		
		physicalMemory = new byte[this.size][this.frameSize];
	}
	
	/**
	 * Returns the byte referred to by the given address.
	 * 
	 * @param address - the address to be looked up.
	 * @return the 8 bit signed int referred to.
	 */
	public int lookup(int address){
		// The frame number and offset will be parsed.
		int frame = (address & 0x0000FF00) >> 8;
		int offset = address & 0xFF;
		return physicalMemory[frame][offset];
	}
	
	/**
	 * Adds the given data to memory and returns the frame that was updated.
	 * Since the frames are updated sequentially, we must ensure that the
	 * correct frame number is referred to.
	 * 
	 * @param data - the bytes to be added to memory
	 * @return the frame that the data was added to.
	 */
	public int insert(byte[] data){
		int frame = nextIndex;
		if(nextIndex < size){
			physicalMemory[nextIndex] = data;
			nextIndex++; // The next available index is updated.
		}
		return frame; // Note, this needs improved, right now it seems dodgey.
	}
	
	/**
	 * Returns the number of frames in memory
	 * 
	 * @return number of frames
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Returns the size of a single frame
	 * 
	 * @return frame size
	 */
	public int getFrameSize(){
		return frameSize;
	}
}
