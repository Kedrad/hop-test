package com.kedrad.hoptest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrossoverHopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrossoverHopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrossoverHopFragment extends Fragment {

    //Result of dividing operated leg's average by not operated leg's average
    public float result;

    //Views
    private EditText etOpLeg1, etOpLeg2, etNopLeg1, etNopLeg2;
    private TextView tvResult;

    private OnFragmentInteractionListener mListener;

    public CrossoverHopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CrossoverHopFragment.
     */
    public static CrossoverHopFragment newInstance() {
        return new CrossoverHopFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crossover_hop, container, false);

        etOpLeg1 = view.findViewById(R.id.editTextOp1);
        etOpLeg2 = view.findViewById(R.id.editTextOp2);
        etNopLeg1 = view.findViewById(R.id.editTextNop1);
        etNopLeg2 = view.findViewById(R.id.editTextNop2);
        tvResult = view.findViewById(R.id.textViewResult);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Method for checking if input in all four EditTexts is valid
    public boolean validateInput(){
        EditText[] et = new EditText[] {etOpLeg1, etOpLeg2, etNopLeg1, etNopLeg2};

        for(int i = 0; i < et.length; i++){
            if(et[i].getText().toString().length() <= 0){
                return false;
            }
        }
        return true;
    }

    public void calculateResult(){
        //Getting measurements for both legs from EditTexts
        float opLeg1 = Float.parseFloat(etOpLeg1.getText().toString());
        float opLeg2 = Float.parseFloat(etOpLeg2.getText().toString());
        float nopLeg1 = Float.parseFloat(etNopLeg1.getText().toString());
        float nopLeg2 = Float.parseFloat(etNopLeg2.getText().toString());

        //Calculating average
        float opLegAverage = (opLeg1 + opLeg2) / 2;
        float nopLegAverage = (nopLeg1 + nopLeg2) / 2;

        result = opLegAverage / nopLegAverage;

        //Showing the result
        tvResult.setText(String.format(Locale.US,"%.2f", result));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
