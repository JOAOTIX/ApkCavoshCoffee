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


import pe.edu.senati.ApkCavoshCoffee.MainActivity;
import pe.edu.senati.ApkCavoshCoffee.R;
import pe.edu.senati.ApkCavoshCoffee.controller.UsuarioController;
import pe.edu.senati.ApkCavoshCoffee.databinding.FragmentSplashBinding;

public class Splash extends Fragment {
    FragmentSplashBinding binding;
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
    binding = FragmentSplashBinding.inflate(inflater);
    return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        navController = Navigation.findNavController(view);

        binding.btnIniciar.setOnClickListener(view1 -> navController.navigate(R.id.navigation_login));

        UsuarioController controller = new UsuarioController(context);

        MainActivity.usuario=controller.getUsuario();

        if (MainActivity.usuario.isLogin()){
            navController.navigate((R.id.navigation_home));
        }

        binding.btnIniciar.setOnClickListener(view1 -> navController.navigate(R.id.navigation_login));
    }
}