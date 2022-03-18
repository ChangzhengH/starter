import java.util.ArrayList;
import java.util.List;

class Model
{
	ArrayList<Tube> tubes;
	Mario mario;

	Model()
	{
        this.tubes = new ArrayList<Tube>();
        this.mario = new Mario(100,200);
	}

	void unmarshal(Json ob){//unmarshaling from a Json object
		Json jsonTubes = ob.get("tubes");
		this.tubes = new ArrayList<Tube>();
		for(int i = 0; i < jsonTubes.size(); i++)
			this.tubes.add(new Tube(jsonTubes.get(i)));
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
		//notice this check must be before the update
		if( isMarioOverlappedTube() ){
			this.mario.goBackPrevPos();
		}

		this.mario.update();

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

	//detect whether or not mario overlapped a tube
	boolean isMarioOverlappedTube(){

		if( this.tubes.isEmpty() )//tubes为空自然是没有重合
			return false;
		for(Tube t : this.tubes){
			if(this.mario.mario_x+ this.mario.width < t.xPosition)
				return false;
			if(this.mario.mario_x > t.xPosition+t.width)
				return false;
			if(this.mario.mario_y+ this.mario.height < t.yPosition) // assumes bigger is downward
				return false;
			if(this.mario.mario_y > t.yPosition+t.height) // assumes bigger is downward
				return false;
		}
		return true;
	}
}
