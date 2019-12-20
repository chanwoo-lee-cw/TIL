package day11;

public class TV {

	private String model;
	private int size;
	private int channel;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	TV() {
	}

	TV(String model, int size, int channel) {
		setModel(model);
		setSize(size);
		setChannel(channel);
	}

	public void channelUp() {
		this.channel += 1;
		if (this.channel > 10)
			this.channel = 1;
	}

	public void channelDown() {
		this.channel -= 1;
		if (this.channel < 1)
			this.channel = 10;
	}

}
