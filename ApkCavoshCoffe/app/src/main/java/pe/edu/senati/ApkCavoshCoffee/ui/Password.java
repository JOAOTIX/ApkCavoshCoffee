package pe.edu.senati.ApkCavoshCoffee.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.senati.ApkCavoshCoffee.R;
import pe.edu.senati.ApkCavoshCoffee.databinding.FragmentRegisterBinding;

public class Password extends Fragment {
    FragmentRegisterBinding binding;
    View view;
    Context context;
    NavController navController;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        binding.btnSignIn.setOnClickListener(view1 -> navController.navigate(R.id.navigation_login));
        binding.tvLoginNow.setOnClickListener(view1 -> navController.navigate(R.id.navigation_login));
    }
}