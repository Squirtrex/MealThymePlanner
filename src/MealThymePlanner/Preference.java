class Preference {
  double preference;
  int rated_count;
  int rating_total;
  
  public Preference()
  {
    this.preference = 0.0;
    this.rated_count = 0;
    this.rating_total =0;
  }
  
  public double getPreference() {
    return this.preference;
  }
  
  public void setPreference(double new_preference) {
    this.preference = new_preference;
  }
  
  //Used when updating preferences due to user rating a recipe.
  //This way we keep running averages of the ratings for each ingredient
  public void updatePreference(int rating) {
    this.rated_count += 1;
    this.rating_total += rating;
    this.preference = (double)rating_total / rated_count;
  }
  
}
