/**
 * The Country class for this program houses the data fields, getters, setters,
 * and other methods that will be utilized in order to make an instance (object)
 * of type "Country." The data fields stores information for each instance of
 * "Country" that is made. This includes the country name, code, capitol,
 * population, GDP, and happiness rank. These data fields are of type String, as
 * they are being read from a file. In addition to the data fields, class
 * Country has two constructors, six setter and getter methods for each of the
 * data fields, a compareCountry method, a printCountryObject method, and a
 * toString method. This class will be called from the main class in order to
 * set and access the data fields.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on April 19, 2020>
 */

class Country {
	/**
	 * variables (data fields)
	 */
	private String Name;
	private String Code;
	private String Capitol;
	private String Population;
	private String GDP;
	private String HappinessRank;
	private Double GDPperCapita;

	/**
	 * This is the default constructor for the Country class, which Java provides
	 * for the program. The constructor is what initializes instances (objects) of a
	 * class. There are no parameters or return values.
	 */
	public Country() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This is a constructor for the Country class for which the data fields have
	 * specified values. The constructor is what initializes instances (objects) of
	 * a class. There are no return values.
	 * 
	 * @param N  - initializes data field for Name
	 * @param Co - initializes data field for Code
	 * @param Ca - initializes data field for Capitol
	 * @param Po - initializes data field for Population
	 * @param G  - initializes data field for GDP
	 * @param HR - initializes data field for HappinessRank
	 */
	public Country(String N, String Co, String Ca, String Po, String G, String HR, Double GperC) {
		Name = N;
		Code = Co;
		Capitol = Ca;
		Population = Po;
		GDP = G;
		HappinessRank = HR;
		GDPperCapita = GperC;
	}

	/**
	 * for this Country class, there are 6 getter (accessors) and 6 setters
	 * (mutators) for each data field. Getters and setters provide the means for
	 * reading and writing the values to the data fields in an indirect way,
	 * especially since the data fields are private. This is the practice of
	 * encapsulation.
	 */

	/**
	 * This getter allows access to the Name.
	 * 
	 * @return Name - returns the value of the data field Name.
	 */
	public String getName() {
		return this.Name;
	}

	/**
	 * This getter allows access to the Code.
	 * 
	 * @return Code - returns the value of the data field Code.
	 */
	public String getCode() {
		return this.Code;
	}

	/**
	 * This getter allows access to the Capitol.
	 * 
	 * @return Capitol - returns the value of the data field Capitol.
	 */
	public String getCapitol() {
		return this.Capitol;
	}

	/**
	 * This getter allows access to the Population.
	 * 
	 * @return Population - returns the value of the data field Population.
	 */
	public String getPopulation() {
		return this.Population;
	}

	/**
	 * This getter allows access to the GDP.
	 * 
	 * @return GDP - returns the value of the data field GDP.
	 */
	public String getGDP() {
		return this.GDP;
	}

	/**
	 * This getter allows access to the Happiness Rank.
	 * 
	 * @return HappinessRank - returns the value of the data field HappinessRank.
	 */
	public String getHappinessRank() {
		return this.HappinessRank;
	}

	/**
	 * This getter allows access to the GDPperCapita.
	 * 
	 * @return GDPperCapita - returns the value of the data field GDPperCapita.
	 */
	public Double getGDPperCapita() {
		return this.GDPperCapita;
	}

	/**
	 * This setter redefines the value of Name.
	 * 
	 * @param Name - sets the initial value for Name.
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * This setter redefines the value of Code.
	 * 
	 * @param Code - sets the initial value for Code.
	 */
	public void setCode(String Code) {
		this.Code = Code;
	}

	/**
	 * This setter redefines the value of Capitol.
	 * 
	 * @param Capitol - sets the initial value for Capitol.
	 */
	public void setCapitol(String Capitol) {
		this.Capitol = Capitol;
	}

	/**
	 * This setter redefines the value of Population.
	 * 
	 * @param Population - sets the initial value for Population.
	 */
	public void setPopulation(String Population) {
		this.Population = Population;
	}

	/**
	 * This setter redefines the value of GDP.
	 * 
	 * @param GDP - sets the initial value for GDP.
	 */
	public void setGDP(String GDP) {
		this.GDP = GDP;
	}

	/**
	 * This setter redefines the value of HappinessRank.
	 * 
	 * @param HappinessRank - sets the initial value of HappinessRank.
	 */
	public void setHappinessRank(String HappinessRank) {
		this.HappinessRank = HappinessRank;
	}

	/**
	 * This setter redefines the value of GDPperCapita.
	 * 
	 * @param GDPperCapita - sets the initial value of GDPperCapita.
	 */
	public void setGDPperCapita(Double GDPperCapita) {
		this.GDPperCapita = GDPperCapita;
	}

	/**
	 * The purpose for the compareCountry method within the Country class is to
	 * compare this.Name to the object Name based on the Name string. Furthermore,
	 * this method is useful for comparing the user's input to the countries to
	 * check if it exists. The method returns 0, -1, or 1 if this.Name is null,
	 * Name2.Name is null, or both is null. Otherwise, it returns a value that
	 * compares the index of this.Name and compares it to Name2.Name.
	 * 
	 * @param Name2 - initializes Country object in order to compare to this.Name.
	 * @return 0 - returns if this.Name and Name2.Name is null.
	 * @return -1 - returns if only this.Name is null.
	 * @return 1 - returns if only Name2.Name is null.
	 * @return this.Name.compareTo(Name2.Name) - returns if the values are not null.
	 */
	public int compareCountry(Country Name2) {
		if (this.Name == null) {
			if (Name2.Name == null) {
				return 0;
			} else {
				return -1;
			}
		} else if (Name2.Name == null) {
			return 1;
		} else {
			return this.Name.compareTo(Name2.Name);
		}
	}

	/**
	 * The purpose of the printCountryObject method within the Country class is to
	 * print a single "Country" object by simply calling the getters of each data
	 * field in order to print formatted Individual Country Report. There are no
	 * parameters or return values for this method.
	 */
	public void printCountryObject() {
		System.out.println("");
		System.out.println("Individual Country report");
		System.out.println("--------------------------");
		System.out.printf("Name: %s\n", this.getName());
		System.out.printf("Code: %s\n", this.getCode());
		System.out.printf("Capitol: %s\n", this.getCapitol());
		System.out.printf("Population: %s\n", this.getPopulation());
		System.out.printf("GDP: %s\n", this.getGDP());
		System.out.printf("Happiness Rank: %s\n", this.getHappinessRank());
	}

	/**
	 * This method returns the string representation of the data fields of the
	 * object. This ensures that the output will not show the address location, but
	 * the actual representation itself. There are no parameters.
	 * 
	 * @return String.format("%-35s%-21s%-20s%-20s%-20.2f%-20s", Name, Code,
	 *         Capitol, Population, GDPperCapita, HappinessRank) - This returns the
	 *         specific and proper format for the display of the data fields.
	 */
	public String toString() {
		return String.format("%-35s%-21s%-20s%-20s%-20.2f%-20s", Name, Code, Capitol, Population, GDPperCapita,
				HappinessRank);
	}
}
