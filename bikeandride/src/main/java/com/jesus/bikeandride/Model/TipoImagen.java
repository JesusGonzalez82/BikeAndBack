package com.jesus.bikeandride.Model;

    public enum TipoImagen {
        USUARIO("usuario"),
        BICICLETA("bicicleta"),
        RUTA("ruta"),
        ACTIVIDAD("actividad");

        private String valor;

        TipoImagen(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }
