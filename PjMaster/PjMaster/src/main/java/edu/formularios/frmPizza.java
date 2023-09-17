package edu.formularios;

import edu.pizza.base.Topping;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class frmPizza
{
    private JPanel jPanelPrincipal;
    private JComboBox comboBoxToppings;
    private JTextField txtPizza;
    private JButton btnAddIngrediente;
    private JLabel lblTotal;
    private JList lista1;
    private JButton btbPrepararPizza;
    private JList lista2;
    private JComboBox comboBoxPizzas;
    private JRadioButton pequeñaRadioButton;
    private JRadioButton grandeRadioButton;
    private JRadioButton medianaRadioButton;
    private JRadioButton lastSelectedRadioButton;
    private JButton eliminarIngredienteButton;
    private List<Topping> ingredientesSeleccionados = new ArrayList<>();
    private boolean isPequeña = false;
    private boolean isMediana = false;
    private boolean isGrande = false;
    private List<Topping> ingredientes = new ArrayList<>();
    private double total = 0;

    private DefaultListModel modelolista = new DefaultListModel();
    private DefaultListModel modelolista2 = new DefaultListModel();
    public JPanel getjPanelPrincipal()
    {
        return jPanelPrincipal;
    }

    public frmPizza()
    {
        cargarToppings();
        cargarPizzas();
        agruparBotones();



        btnAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();

                modelolista.addElement(ingrediente);
                lista1.setModel(modelolista);
                total += ingrediente.getPrecio();


                lblTotal.setText(String.valueOf(total));

            }
        });

        btbPrepararPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lista1.getModel().getSize() == 0)
                {
                    modelolista2.clear();
                    modelolista2.addElement("Debes agregar ingredientes a tu pizza...");
                    lista2.setModel(modelolista2);

                }
                else if (lastSelectedRadioButton == null)
                {
                    modelolista2.clear();
                    modelolista2.addElement("Debes seleccionar el tamaño de tu pizza...");
                    lista2.setModel(modelolista2);
                }
                else
                {
                    modelolista2.clear();
                    modelolista2.addElement("Preparando " + comboBoxPizzas.getSelectedItem().toString());
                    modelolista2.addElement("Tamaño: " + lastSelectedRadioButton.getText());
                    modelolista2.addElement("Agregando ingredientes.... ");
                    for (int i = 0; i < lista1.getModel().getSize(); i++) {
                        modelolista2.addElement(lista1.getModel().getElementAt(i));
                    }
                    modelolista2.addElement("Total: Q"+lblTotal.getText());
                    modelolista2.addElement("Pizza preparada exitosamente!");
                }
                    lista2.setModel(modelolista2);
                }

        });


        comboBoxPizzas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el elemento seleccionado
                String selectedPizza = (String) comboBoxPizzas.getSelectedItem();

                // Restar el costo de los ingredientes del tipo de pizza anterior
                for (Topping ingrediente : ingredientesSeleccionados) {
                    total -= ingrediente.getPrecio();
                }

                // Limpiar la lista de ingredientes
                modelolista.clear();

                // Agregar ingredientes dependiendo del nuevo tipo de pizza seleccionado
                ingredientesSeleccionados.clear(); // Limpiar la lista de ingredientes seleccionados
                switch (selectedPizza) {
                    case "Pizza Italiana":
                        ingredientes.add(new Topping("Salsa italiana", 10));
                        modelolista.addElement("Salsa italiana Q10");
                        ingredientesSeleccionados.add(new Topping("Salsa italiana", 10));
                        total += 10;
                        break;
                    case "Pizza Mexicana":
                        ingredientes.add(new Topping("Chile", 10));
                        modelolista.addElement("Chile Q10");
                        ingredientes.add(new Topping("Frijoles", 12));
                        modelolista.addElement("Frijoles Q12");
                        ingredientesSeleccionados.add(new Topping("Chile", 10));
                        ingredientesSeleccionados.add(new Topping("Frijoles", 12));
                        total += 22;
                        break;
                    case "Pizza Americana":
                        ingredientes.add(new Topping("Peperoni", 14));
                        modelolista.addElement("Peperoni Q14");
                        ingredientes.add(new Topping("Queso", 15));
                        modelolista.addElement("Queso Q15");
                        ingredientesSeleccionados.add(new Topping("Peperoni", 14));
                        ingredientesSeleccionados.add(new Topping("Queso", 15));
                        total += 29;
                        break;
                    case "Pizza Vegetariana":
                        ingredientes.add(new Topping("Albahaca", 18));
                        modelolista.addElement("Albahaca Q18");
                        ingredientes.add(new Topping("Salsa de Tomate", 19));
                        modelolista.addElement("Salsa de Tomate Q19");
                        ingredientesSeleccionados.add(new Topping("Albahaca", 18));
                        ingredientesSeleccionados.add(new Topping("Salsa de Tomate", 19));
                        total += 37;
                        break;
                    case "Pizza Española":
                        ingredientes.add(new Topping("Jamon Serrano", 20));
                        modelolista.addElement("Jamon Serrano Q20");
                        ingredientes.add(new Topping("Aceitunas", 13));
                        modelolista.addElement("Aceitunas Q13");
                        ingredientesSeleccionados.add(new Topping("Jamon Serrano", 20));
                        ingredientesSeleccionados.add(new Topping("Aceitunas", 13));
                        total += 33;
                        break;
                    // Agregar otros casos para tipos de pizza adicionales si es necesario
                }

                // Actualizar la JList con los nuevos ingredientes
                lista1.setModel(modelolista);
                lblTotal.setText(String.valueOf(total));
            }
        });

        eliminarIngredienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Topping ingrediente = (Topping) lista1.getSelectedValue();
                modelolista.removeElement(ingrediente);
                lista1.setModel(modelolista);
                total -= ingrediente.getPrecio();
                lblTotal.setText(String.valueOf(total));
            }
        });

        pequeñaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPequeña == false) {
                    if (lastSelectedRadioButton != null) {
                        // Revertir el cambio de la última selección
                        total /= (lastSelectedRadioButton == medianaRadioButton) ? 1.10 : 1.15;
                    }
                    total *= 1.05; // Aumenta el total en un 5%
                    lblTotal.setText(String.valueOf(total));
                    lastSelectedRadioButton = pequeñaRadioButton;

                    isPequeña = true;
                    isMediana = false;
                    isGrande = false;
                }
            }
        });

        medianaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMediana == false) {
                    if (lastSelectedRadioButton != null) {
                        // Revertir el cambio de la última selección
                        total /= (lastSelectedRadioButton == pequeñaRadioButton) ? 1.05 : 1.15;
                    }
                    total *= 1.10; // Aumenta el total en un 10%
                    lblTotal.setText(String.valueOf(total));
                    lastSelectedRadioButton = medianaRadioButton;

                    isPequeña = false;
                    isMediana = true;
                    isGrande = false;
                }
            }
        });

        grandeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isGrande == false) {
                    if (lastSelectedRadioButton != null) {
                        // Revertir el cambio de la última selección
                        total /= (lastSelectedRadioButton == pequeñaRadioButton) ? 1.05 : 1.10;
                    }
                    total *= 1.15; // Aumenta el total en un 15%
                    lblTotal.setText(String.valueOf(total));
                    lastSelectedRadioButton = grandeRadioButton;

                    isPequeña = false;
                    isMediana = false;
                    isGrande = true;
                }
            }
        });
    }

public void agruparBotones()
    {
    ButtonGroup grupobotones = new ButtonGroup();
        grupobotones.add(pequeñaRadioButton);
        grupobotones.add(medianaRadioButton);
        grupobotones.add(grandeRadioButton);
    }
    //cargar toppings
    private void cargarToppings()
    {
        ingredientes.add(new Topping("Champiñones", 10));
        ingredientes.add(new Topping("Tomate", 11));
        ingredientes.add(new Topping("Cebolla", 6));
        ingredientes.add(new Topping("Chucho", 9));
        ingredientes.add(new Topping("Salchica", 15));
        ingredientes.add(new Topping("Anchoas", 18));

        DefaultComboBoxModel model = new DefaultComboBoxModel(ingredientes.toArray());
        comboBoxToppings.setModel(model);

    }

    private void cargarPizzas()
    {
        comboBoxPizzas.addItem("Yo la armo");
        comboBoxPizzas.addItem("Pizza Italiana");
        comboBoxPizzas.addItem("Pizza Mexicana");
        comboBoxPizzas.addItem("Pizza Americana");
        comboBoxPizzas.addItem("Pizza Vegetariana");
        comboBoxPizzas.addItem("Pizza Española");

    }

}
