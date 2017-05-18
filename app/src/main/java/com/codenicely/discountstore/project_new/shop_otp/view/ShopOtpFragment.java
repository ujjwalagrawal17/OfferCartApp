package com.codenicely.discountstore.project_new.shop_otp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.shop_home.ShopHomePage;
import com.codenicely.discountstore.project_new.shop_otp.model.RetrofitShopOtpProvider;
import com.codenicely.discountstore.project_new.shop_otp.presenter.ShopOtpPresenter;
import com.codenicely.discountstore.project_new.shop_otp.presenter.ShopOtpPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopOtpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopOtpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ShopOtpFragment extends Fragment implements ShopOtpView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;
	ShopOtpPresenter shopOtpPresenter;

	@BindView(R.id.editText)
	EditText otpEditText;
	@BindView(R.id.progressBarView)
	ProgressBar progressBar;
	@BindView(R.id.button)
	Button sendOtpButton;
	String mobile;
	String otp1;
	private SharedPrefs sharedPrefs;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ShopOtpFragment.
	 */

	// TODO: Rename and change types and number of parameters
	public static ShopOtpFragment newInstance(String param1, String param2) {
		ShopOtpFragment fragment = new ShopOtpFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	public ShopOtpFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v =inflater.inflate(R.layout.fragment_shop_otp, container, false);
		ButterKnife.bind(this, v);
		sharedPrefs = new SharedPrefs(getContext());
		mobile = getArguments().getString("mobile");
		shopOtpPresenter =new ShopOtpPresenterImpl(this,new RetrofitShopOtpProvider());

		sendOtpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				otp1= otpEditText.getText().toString();
				if (otp1 .equals("") || otp1.equals(null)) {
					otpEditText.setError("Please enter One Time Password");
					otpEditText.requestFocus();
				}
		shopOtpPresenter.requestOtp(otp1,mobile);
			}
		});
		// Inflate the layout for this fragment
		return v;
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

	@Override
	public void showMessage(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void showProgressbar(boolean show) {
		if (show) {
			progressBar.setVisibility(View.VISIBLE);
			sendOtpButton.setEnabled(true);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
			sendOtpButton.setEnabled(true);
		}
	}

	@Override
	public void onOtpVerified(String access_token) {

		Log.d("res", access_token);

		sharedPrefs.setAccessTokenShop(access_token);
		sharedPrefs.setShopLogin(true);

		Intent intent = new Intent(getContext(), ShopHomePage.class);
		startActivity(intent);

		//something to jump to next fragment

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