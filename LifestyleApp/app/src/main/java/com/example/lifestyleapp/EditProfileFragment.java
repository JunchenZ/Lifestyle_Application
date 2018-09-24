package com.example.lifestyleapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.AdapterView.*;


/**
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener, OnItemSelectedListener {

    // ID's for the sex buttons
    private static final int FEMALE = 0;
    private static final int MALE = 1;

    // View objects
    private EditText m_etName;
    private Spinner m_spAge ;
    private Spinner m_spCountry;
    private Spinner m_spCity;
    private Spinner m_spHeight;
    private Spinner m_spWeight;
    private RadioGroup m_sexRadioButtons;
    private RadioButton m_Male;
    private RadioButton m_Female;
    private Button mSubmitButton;

    // Profile data
    private String mName;
    private int mAge;
    private String mCountry;
    private String mCity;
    private int mHeight; // In inches
    private int mWeight; // In pounds
    private boolean mSex; // 0 = female, 1 = male
    private Bitmap mPicture;

    /**
     * Required empty constructor
     */
    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        // Set up the button and register it for clicks
        mSubmitButton = (Button) view.findViewById(R.id.button_profile_edit_submit);
        mSubmitButton.setOnClickListener(this);

        // Assign all Views as members for easy access within class
        m_etName = view.findViewById(R.id.et_name);
        m_spAge = view.findViewById(R.id.spinner_age);
        m_spCountry = view.findViewById(R.id.spinner_country);
        m_spCountry.setOnItemSelectedListener(this); // So we can get country to populate cities
        m_spCity = view.findViewById(R.id.spinner_city);
        m_spHeight = view.findViewById(R.id.spinner_height);
        m_spWeight = view.findViewById(R.id.spinner_weight);
        m_sexRadioButtons = view.findViewById(R.id.rg_sex);
        m_Male = view.findViewById(R.id.radio_male);
        m_Female = view.findViewById(R.id.radio_female);
        // Sets the ID's of the radio buttons so that later it can be determined which was clicked.
        m_Male.setId(MALE);
        m_Female.setId(FEMALE);

        // FILL THE SPINNERS WITH DATA
        setSpinners();

        return view;
    }

    /**
     * Helper method that sets all of the data into the following Spinners:
     * Age, Height, Weight, and Country.
     */
    private void setSpinners() {

        // Set age spinner
        ArrayList<Integer> ageValues = new ArrayList<>();
        for(int i = 12; i < 111; i++){
            ageValues.add(i);
        }
        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, ageValues);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spAge.setAdapter(ageAdapter);

        // Set Height spinner (
        ArrayList<String> heightValues = new ArrayList<>();
        for(int feet = 4; feet < 9; feet++){ // account for feet
            String height = feet + "\'";
            heightValues.add(height);
            for(int inches = 1; inches < 12; inches++){ // acount for inches
                heightValues.add(height + inches + "\"");
            }
        }
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, heightValues);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spHeight.setAdapter(heightAdapter);

        // Set Weight spinner (
        ArrayList<Integer> weightValues = new ArrayList<>();
        for(int i = 50; i < 400; i++){ // account for feet
            weightValues.add(i);
        }
        ArrayAdapter<Integer> weightAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, weightValues);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spWeight.setAdapter(weightAdapter);

        // Set Country spinner
        String[] countries = getResources().getStringArray(R.array.countries_array); // Countries are in res folder
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spCountry.setAdapter(countryAdapter);
        m_spCountry.setSelection(227); // Sets United States as default country

    }

    /**
     * When a user clicks on the submit button, all of the following data is collected and stored
     * in the member variables: Name, Age, Weight, Height, Country, City, Sex.
     *
     * @param view - View
     */
    @Override
    public void onClick(View view) {
        // GET NAME, AGE, WEIGHT, & LOCAION DATA
        mName = m_etName.getText().toString();
        mAge = Integer.parseInt(m_spAge.getSelectedItem().toString());
        mWeight = Integer.parseInt((m_spWeight.getSelectedItem().toString()));
        mCountry = m_spCountry.getSelectedItem().toString();
        mCity = m_spCity.getSelectedItem().toString();

        // GET HEIGHT DATA
        String heightAsString = m_spHeight.getSelectedItem().toString();
        // Replace all non number characters with a space
        heightAsString = heightAsString.replaceAll("[^-?0-9]+", " ");
        // Split all numbers into their own string
        String[] splitHeight = heightAsString.split(" ");
        mHeight = Integer.parseInt(splitHeight[0].trim()) * 12; // feet*inches
        if(splitHeight.length > 1){
            mHeight += Integer.parseInt(splitHeight[1].trim()); // Add extra inches
        }

        // GET SEX DATA
        // Get the ID of the user-selected button (Female = 0, Male = 1)
        int sexAsInteger = m_sexRadioButtons.getCheckedRadioButtonId();
        mSex =  (sexAsInteger == 1) ? true : false;
    }

    /**
     * Triggered when a user selects their country. The City Spinner is then
     * populated with the cities that correspond to the selected country.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Get the name of the selected country
        String country = (String) parent.getItemAtPosition(position);

        // Set City spinner Based on the selected country
        final int countriesAndCities = R.raw.countries_cities; // ID for json file with cities
        try {
            // Read the JSON file with an InputStream
            InputStream is = getResources().openRawResource(countriesAndCities);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Get a JSONObject/JSONArray of the needed cities
            String countriesCitiesJSON = new String(buffer, "UTF-8");
            JSONObject jObject = new JSONObject(countriesCitiesJSON);
            JSONArray citiesInJSON = (JSONArray) jObject.get(country);

            // Convert JSONArray to a basic array
            String[] citiesAsArray = new String[citiesInJSON.length()];
            for(int i = 0; i < citiesInJSON.length(); i++){
                citiesAsArray[i] = citiesInJSON.getString(i);
            }
            Arrays.sort(citiesAsArray);

            // Set the city Spinner
            ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, citiesAsArray);
            cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            m_spCity.setAdapter(cityAdapter);

        } catch (IOException e) { // Reading the file failed
            e.printStackTrace();
        } catch (JSONException e) { // JSON stuff failed
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }

}
