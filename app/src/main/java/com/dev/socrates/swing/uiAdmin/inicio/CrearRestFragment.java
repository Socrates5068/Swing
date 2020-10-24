package com.dev.socrates.swing.uiAdmin.inicio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.socrates.swing.MainMenuAdmin;
import com.dev.socrates.swing.R;
import com.dev.socrates.swing.connect.Link;
import com.dev.socrates.swing.connect.RestData;
import com.dev.socrates.swing.connect.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearRestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearRestFragment extends Fragment implements View.OnClickListener {
    Button takePhoto, Guardar;
    static final int PERMISION_CODE = 10;
    static final int code_camera = 999;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CrearRestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearRestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearRestFragment newInstance(String param1, String param2) {
        CrearRestFragment fragment = new CrearRestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_rest, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadComponets();
    }

    private void loadComponets() {
        takePhoto = this.getActivity().findViewById(R.id.tomarFoto);
        takePhoto.setOnClickListener(this);

        Guardar = getActivity().findViewById(R.id.guardarRest);
        Guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //guardar un restaurante
                AsyncHttpClient client = new AsyncHttpClient();

                EditText nombre = getActivity().findViewById(R.id.nombreRest);
                EditText direccion = getActivity().findViewById(R.id.calleRest);
                EditText telefono = getActivity().findViewById(R.id.telRest);

                RequestParams params = new RequestParams();
                params.add("Nombre", nombre.getText().toString());
                params.add("Calle", direccion.getText().toString());
                params.add("Telefono", telefono.getText().toString());
                //params.add("fotoRest", telefono.getText().toString());

                client.post(Link.RESTAURANT_SERVICE, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            RestData.MSN = response.getString("msn");
                            if (RestData.MSN.equals("Restaurant Creado Correctamente")) {
                                Toast.makeText(getActivity(), RestData.MSN, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), RestData.MSN, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                Navigation.findNavController(v).navigate(R.id.action_crearRestFragment_to_navigation_home);
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISION_CODE);
        }
    }

    public Boolean checkPermission(String permission) {
        int result = this.getActivity().checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View v) {
        if (checkPermission(Manifest.permission.CAMERA)) {
            callCamera();
            //Toast.makeText(this, "TIENES PERMISOS 🤔", Toast.LENGTH_LONG).show();
        } else {
            requestPermission();
            //Toast.makeText(this, "No tienes los permisos 😌", Toast.LENGTH_LONG).show();
        }
    }

    private void callCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camera.resolveActivity(this.getActivity().getPackageManager()) != null) {
            this.startActivityForResult(camera, code_camera);
        }
    };

    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code_camera && resultCode == RESULT_OK) {
            Bundle photo = data.getExtras();
            Bitmap imageBitmap = (Bitmap) photo.get("data");
            ImageView img = this.getActivity().findViewById(R.id.fotoRest);
            img.setImageBitmap(imageBitmap);
        }
    }
}