package edu.upi.cs.yudiwbs.uas_template;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upi.cs.yudiwbs.uas_template.databinding.FragmentDuaBinding;

public class FragmentDua extends Fragment implements SensorEventListener{

    private FragmentDuaBinding binding;

    ArrayList<Hasil> alHasil = new ArrayList<>();
    AdapterHasil adapter;
    RecyclerView.LayoutManager lm;

    String TAG = "debug_kel6";

    public FragmentDua() {
        // Required empty public constructor
    }

    public static FragmentDua newInstance(String param1, String param2) {
        FragmentDua fragment = new FragmentDua();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private SensorManager sm;
    private Sensor senAccel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getActivity().getSystemService(getActivity().getApplicationContext().SENSOR_SERVICE);
        senAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sm != null && senAccel != null){
            Log.d(TAG,"Sukses, device punya sensor accelerometer!");
        }
        else {
            // gagal, tidak ada sensor accelerometer.
            Log.d(TAG,"Tidak ada sensor accelerometer!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDuaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        View tvHasilView = inflater.inflate(R.layout.fragment_dua, container, false);

        TextView tvHasil = (TextView) tvHasilView.findViewById(R.id.tvHasil);
        tvHasil.setText("Mulai");

        adapter = new AdapterHasil(alHasil);
        binding.rvHasil.setAdapter(adapter);

        lm = new LinearLayoutManager(getActivity());
        binding.rvHasil.setLayoutManager(lm);

        //supaya ada garis antar row
        binding.rvHasil.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        binding.buttonFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alHasil.add(new Hasil("HP Diangkat"));
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double ax=0,ay=0,az=0;
        boolean isTabrakan = false;
        // menangkap perubahan nilai sensor
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            ax=sensorEvent.values[0];
            ay=sensorEvent.values[1];
            az=sensorEvent.values[2];
        }
        if  (az>=20) {
            isTabrakan = true;
        }
        if (isTabrakan) {
            Log.d(TAG, "HP Diangkat");
            //tvHasil.setText("TABRAKAN!!");
        } else{
            long timestamp = System.currentTimeMillis();
            // Menampilkan log dari accelerometer beserta timestamp
            String msg = "X: " + ax + ", Y: " + ay + ", Z: " + az + ", Timestamp: " + timestamp;
            Log.d("Position", msg);
            //tvHasil.setText(msg);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sm.registerListener(this, senAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }
}