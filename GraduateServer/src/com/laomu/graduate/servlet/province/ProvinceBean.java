package com.laomu.graduate.servlet.province;

/**省 bean*/
public class ProvinceBean {

	public int id;

	public String name;
	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:" +id + " name=" +name;
	}
}
