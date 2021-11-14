import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
	public final class RearrangingCars {
	private RearrangingCars() {}
	public static class Move {
	public Move(int car, char from, char to) {
	this.car_ = car;
	this.from_ = from;
	this.to_ = to;
	}
	@Override
	public boolean equals(Object o) {
	Move other = (Move) o;
	return car_ == other.car_ && from_ == other.from_ && to_ == other.to_;
	}
	int car_;
	char from_;
	char to_;
	}
	public static class Parking {
	public Parking(Map<Integer, Character> carToLotMapping) {
	this.carToLotMapping_.putAll(carToLotMapping);
	for (Map.Entry<Integer, Character> entry : carToLotMapping_.entrySet()) {
	lotToCarMapping_.put(entry.getValue(), entry.getKey());
	}
	emptyLot_ = findEmptyLot();
	}
	Move moveToEmpty(int car) {
	char currentLot = carToLotMapping_.get(car);
	carToLotMapping_.put(car, emptyLot_);
	lotToCarMapping_.put(emptyLot_, car);
	char newLot = emptyLot_;
	emptyLot_ = currentLot;
	return new Move(car, currentLot, newLot);
	}
	public char emptyLot_;
	public Map<Integer, Character> carToLotMapping_ = new HashMap<>();
	public Map<Character, Integer> lotToCarMapping_ = new HashMap<>();
	char findEmptyLot() {
	for (char letter = 'A'; letter <= 'Z'; letter++) {
	if (!lotToCarMapping_.containsKey(letter)) {
	return letter;
	}
	}
	return 0;
	}
	}
	public static Iterable<Move> rearrangeCars(Parking initialParking, Parking
	finalParking) {
	List<Move> moves = new ArrayList<>();
	boolean solved = false;
	while (!solved) {
	if (initialParking.emptyLot_ == finalParking.emptyLot_) {
	// The empty lot is correct, check if any car is still misplaced.
	solved = true;
	for (Map.Entry<Integer, Character> entry :
	initialParking.carToLotMapping_.entrySet()) {
	int car = entry.getKey();
	char lot = entry.getValue();
	if (finalParking.carToLotMapping_.get(car) != lot) {
	solved = false;
	moves.add(initialParking.moveToEmpty(car));
	break;
	}
	}
	}
	// Current empty lot should not be empty; move the right car there.
	if (!solved) {
	int carToMove = finalParking.lotToCarMapping_.get(initialParking.emptyLot_);
	moves.add(initialParking.moveToEmpty(carToMove));
	}
	}
	return moves;
	}
	  
		  public static void main(String args[]) {
			  Map<Integer, Character> start=new HashMap<Integer,Character>();
			  start.put(1, 'A');
			  start.put(2, 'B');
			  start.put(null, 'C');
			  start.put(3, 'D');
			  
			  Map<Integer, Character> end=new HashMap<Integer,Character>();
			  end.put(1, 'B');
			  end.put(2, 'C');
			  end.put(3, 'A');
			  end.put(null, 'D');
					  
			  Parking begin=new Parking(start);
			  Parking fin=new Parking(end);
			  
			  Iterable<Move> out=rearrangeCars(begin,fin);
			}
}


