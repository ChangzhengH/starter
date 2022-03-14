import java.util.ArrayList;

class Model
{
	ArrayList<Tube> tubes;

	Model()
	{
        this.tubes = new ArrayList<Tube>();
	}

	Model(Json ob){//unmarshaling constructor
		Json tmpList = ob.get("tubes");
		this.tubes = new ArrayList<Tube>();
		for(int i = 0; i < tmpList.size(); i++)
			this.tubes.add(new Tube(tmpList.get(i)));
	}

	Json marshal(){
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("tubes", tmpList);
		for(int i = 0; i < this.tubes.size(); i++)
			tmpList.add(this.tubes.get(i).marshal());
		return ob;
	}

	public void update()
	{

	}

	//controller calls it and pass x-y position to it. The logic is to remove a existing tube or create a new one
	public void createOrRemoveTube(int x, int y)
	{
		//before creating a tube, check if it clicked on an existing tube.If so, remove it and return not creating a tube
		for(int i = 0; i < tubes.size(); i++){
			Tube t = tubes.get(i);
			if(t.isClicked(x,y))
			{
				tubes.remove(i);
				return;
			}
		}
		//create a tube and put it in ArrayList
        Tube t = new Tube(x,y);
        tubes.add(t);
	}
}
