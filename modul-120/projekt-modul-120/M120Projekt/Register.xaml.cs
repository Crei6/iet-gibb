using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using M120Projekt.Data;

namespace M120Projekt
{
    /// <summary>
    /// Interaktionslogik für Register.xaml
    /// </summary>
    public partial class Register : UserControl
    {
        State state;
        Operator currentOperator;

        MainWindow parent;
        public Register(MainWindow parent, Operator o)
        {
            InitializeComponent();
            this.parent = parent;
            this.currentOperator = o;
            SetForm();
            AdaptFormToState();
        }

        private void SetForm()
        {
            if (currentOperator != null)
            {
                txtOperatorName.Text = currentOperator.OperatorName;
                txtRealName.Text = currentOperator.RichtigerName;
                txtAge.Text = currentOperator.Alter.ToString();
                dpReleaseDate.SelectedDate = currentOperator.Erscheinungsdatum;
                if (currentOperator.Position.Equals("Angriff")) { cbPosition.SelectedItem = cbiAngriff; }
                if (currentOperator.Position.Equals("Verteidigung")) { cbPosition.SelectedItem = cbiVerteidigung; }
                if (currentOperator.Rüstungspunkte == 1)
                {
                    rbtnArmorPoints1.IsChecked = true;
                    rbtnArmorPoints2.IsChecked = false;
                    rbtnArmorPoints3.IsChecked = false;
                    rbtnSpeedPoints1.IsChecked = false;
                    rbtnSpeedPoints2.IsChecked = false;
                    rbtnSpeedPoints3.IsChecked = true;
                }
                if (currentOperator.Rüstungspunkte == 2)
                {
                    rbtnArmorPoints1.IsChecked = false;
                    rbtnArmorPoints2.IsChecked = true;
                    rbtnArmorPoints3.IsChecked = false;
                    rbtnSpeedPoints1.IsChecked = false;
                    rbtnSpeedPoints2.IsChecked = true;
                    rbtnSpeedPoints3.IsChecked = false;
                }
                if (currentOperator.Rüstungspunkte == 3)
                {
                    rbtnArmorPoints1.IsChecked = false;
                    rbtnArmorPoints2.IsChecked = false;
                    rbtnArmorPoints3.IsChecked = true;
                    rbtnSpeedPoints1.IsChecked = true;
                    rbtnSpeedPoints2.IsChecked = false;
                    rbtnSpeedPoints3.IsChecked = false;
                }
                if (currentOperator.Standardoperator)
                {
                    rbtnIsDefaultOperator.IsChecked = true;
                    rbtnIsNotDefaultOperator.IsChecked = false;
                }
                else
                {
                    rbtnIsDefaultOperator.IsChecked = false;
                    rbtnIsNotDefaultOperator.IsChecked = true;
                }
                state = State.View;
            }
            else
            {
                state = State.Empty;
                dpReleaseDate.SelectedDate = DateTime.Now;
            }
        }

        private void ResetForm()
        {
            txtOperatorName.Text = String.Empty;
            txtRealName.Text = String.Empty;
            txtAge.Text = String.Empty;
            dpReleaseDate.SelectedDate = DateTime.Now;
            cbPosition.SelectedItem = cbiAngriff;
            rbtnArmorPoints1.IsChecked = true;
            rbtnArmorPoints2.IsChecked = false;
            rbtnArmorPoints3.IsChecked = false;
            rbtnSpeedPoints1.IsChecked = false;
            rbtnSpeedPoints2.IsChecked = false;
            rbtnSpeedPoints3.IsChecked = true;
            rbtnIsDefaultOperator.IsChecked = false;
            rbtnIsNotDefaultOperator.IsChecked = true;
        }

        private bool IsFormEmpty()
        {
            if (!String.IsNullOrEmpty(txtOperatorName.Text) 
                || !String.IsNullOrEmpty(txtRealName.Text)
                || !String.IsNullOrEmpty(txtAge.Text))
            {
                return false;
            }            
            return true;
        }

        private void AdaptFormToState()
        {
            switch (state)
            {
                case State.Empty:
                    btnNew.IsEnabled = true;
                    btnSave.IsEnabled = false;
                    btnCancel.IsEnabled = false;
                    btnDelete.IsEnabled = false;
                    disableAll();
                    break;
                case State.New:
                    btnNew.IsEnabled = false;
                    btnSave.IsEnabled = true;
                    btnCancel.IsEnabled = true;
                    btnDelete.IsEnabled = false;
                    enableAll();
                    break;
                case State.View:
                    btnNew.IsEnabled = true;
                    btnSave.IsEnabled = false;
                    btnCancel.IsEnabled = false;
                    btnDelete.IsEnabled = true;
                    enableAll();
                    break;
                case State.Changed:
                    btnNew.IsEnabled = false;
                    btnSave.IsEnabled = true;
                    btnCancel.IsEnabled = true;
                    btnDelete.IsEnabled = false;
                    enableAll();
                    break;
            }
        }

        private void enableAll()
        {
            txtOperatorName.IsEnabled = true;
            txtRealName.IsEnabled = true;
            txtAge.IsEnabled = true;
            dpReleaseDate.IsEnabled = true;
            cbPosition.IsEnabled = true;
            rbtnArmorPoints1.IsEnabled = true;
            rbtnArmorPoints2.IsEnabled = true;
            rbtnArmorPoints3.IsEnabled = true;
            rbtnSpeedPoints1.IsEnabled = true;
            rbtnSpeedPoints2.IsEnabled = true;
            rbtnSpeedPoints3.IsEnabled = true;
            rbtnIsDefaultOperator.IsEnabled = true;
            rbtnIsNotDefaultOperator.IsEnabled = true;
        }

        private void disableAll()
        {
            txtOperatorName.IsEnabled = false;
            txtRealName.IsEnabled = false;
            txtAge.IsEnabled = false;
            dpReleaseDate.IsEnabled = false;
            cbPosition.IsEnabled = false;
            rbtnArmorPoints1.IsEnabled = false;
            rbtnArmorPoints2.IsEnabled = false;
            rbtnArmorPoints3.IsEnabled = false;
            rbtnSpeedPoints1.IsEnabled = false;
            rbtnSpeedPoints2.IsEnabled = false;
            rbtnSpeedPoints3.IsEnabled = false;
            rbtnIsDefaultOperator.IsEnabled = false;
            rbtnIsNotDefaultOperator.IsEnabled = false;
        }

        private void New(object sender, RoutedEventArgs e)
        {
            ResetForm();
            state = State.New;
            AdaptFormToState();
        }

        private bool IsFormValid()
        {
            if (txtOperatorName.Text.ToString().Length != 0 && txtRealName.Text.ToString().Length != 0 && txtAge.Text.ToString().Length != 0)
            {
                if (Test_txtOperatorName() && Test_txtRealName() && Test_txtAge()) { return true; }
                else { 
                    MessageBox.Show("Das Formular ist nicht korrekt ausgefüllt!", "Warnung");
                    return false;
                }
            }
            else
            {
                MessageBox.Show("Alle Felder müssen ausgefüllt sein!", "Warnung");
                return false;
            }
        }

        private void Save(object sender, RoutedEventArgs e)
        {
            if (!IsFormValid()) { return; }
            if (state == State.New)
            {
                Operator newOperator = new Operator();
                newOperator.OperatorName = txtOperatorName.Text.ToString();
                newOperator.RichtigerName = txtRealName.Text.ToString();
                newOperator.Alter = int.Parse(txtAge.Text.ToString());
                newOperator.Erscheinungsdatum = dpReleaseDate.SelectedDate.Value;
                if (cbPosition.SelectedItem == cbiAngriff) { newOperator.Position = "Angriff"; }
                if (cbPosition.SelectedItem == cbiVerteidigung) { newOperator.Position = "Verteidigung"; }
                if (rbtnArmorPoints1.IsChecked.Value) { 
                    newOperator.Rüstungspunkte = 1;
                    newOperator.Geschwindigkeitspunkte = 3;
                }
                if (rbtnArmorPoints2.IsChecked.Value) { 
                    newOperator.Rüstungspunkte = 2;
                    newOperator.Geschwindigkeitspunkte = 2;
                }
                if (rbtnArmorPoints3.IsChecked.Value) { 
                    newOperator.Rüstungspunkte = 3;
                    newOperator.Geschwindigkeitspunkte = 2;
                }
                if (rbtnIsDefaultOperator.IsChecked.Value) { newOperator.Standardoperator = true; }
                if (rbtnIsNotDefaultOperator.IsChecked.Value) { newOperator.Standardoperator = false; }

                Int64 newOperatorId = newOperator.Erstellen();
                parent.ToList();
            }
            else if (state == State.Changed)
            {
                Operator changedOperator = Operator.LesenID(currentOperator.OperatorId);
                changedOperator.OperatorName = txtOperatorName.Text.ToString();
                changedOperator.RichtigerName = txtRealName.Text.ToString();
                changedOperator.Alter = int.Parse(txtAge.Text.ToString());
                changedOperator.Erscheinungsdatum = dpReleaseDate.SelectedDate.Value;
                if (cbPosition.SelectedItem == cbiAngriff) { changedOperator.Position = "Angriff"; }
                if (cbPosition.SelectedItem == cbiVerteidigung) { changedOperator.Position = "Verteidigung"; }
                if (rbtnArmorPoints1.IsChecked.Value)
                {
                    changedOperator.Rüstungspunkte = 1;
                    changedOperator.Geschwindigkeitspunkte = 3;
                }
                if (rbtnArmorPoints2.IsChecked.Value)
                {
                    changedOperator.Rüstungspunkte = 2;
                    changedOperator.Geschwindigkeitspunkte = 2;
                }
                if (rbtnArmorPoints3.IsChecked.Value)
                {
                    changedOperator.Rüstungspunkte = 3;
                    changedOperator.Geschwindigkeitspunkte = 2;
                }
                if (rbtnIsDefaultOperator.IsChecked.Value) { changedOperator.Standardoperator = true; }
                if (rbtnIsNotDefaultOperator.IsChecked.Value) { changedOperator.Standardoperator = false; }

                changedOperator.Aktualisieren();
                parent.ToRegister(Operator.LesenID(changedOperator.OperatorId));
            }
        }

        private void Cancel(object sender, RoutedEventArgs e)
        {
            if (!IsFormEmpty() && MessageBox.Show("Ihre Änderungen gehen verloren!\nWollen sie wirklich fortfahren?", "Abbrechen", MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                if (currentOperator != null)
                {
                    parent.ToRegister(Operator.LesenID(currentOperator.OperatorId));
                }
                else
                {
                    ResetForm();
                    state = State.Empty;
                    AdaptFormToState();
                }
            }
        }

        private void Delete(object sender, RoutedEventArgs e)
        {
            if (MessageBox.Show("Wollen sie wirklich löschen?", "Löschen", MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                Operator.LesenID(currentOperator.OperatorId).Loeschen();
                ResetForm();
                state = State.Empty;
                AdaptFormToState();
            }
            
        }

        // Validierung für Operatorname
        private void txtOperatorName_LostFocus(object sender, RoutedEventArgs e)
        {
            Test_txtOperatorName();
        }

        private bool Test_txtOperatorName()
        {
            Regex regex = new Regex("^[a-zA-Z ]*$");
            if (!regex.IsMatch(txtOperatorName.Text))
            {
                lblOperatorName.Content = "Operatorname *darf nur Buchstaben enthalten";
                lblOperatorName.Foreground = Brushes.Red;
                return false;
            }
            else
            {
                lblOperatorName.Content = "Operatorname";
                lblOperatorName.Foreground = Brushes.Black;
                return true;
            }
        }

        // Validierung für Richtiger Name
        private void txtRealName_LostFocus(object sender, RoutedEventArgs e)
        {
            Test_txtRealName();
        }

        private bool Test_txtRealName()
        {
            Regex regex = new Regex("^[a-zA-Z ]*$");
            if (!regex.IsMatch(txtRealName.Text))
            {
                lblRealName.Content = "Richtiger Name *darf nur Buchstaben enthalten";
                lblRealName.Foreground = Brushes.Red;
                return false;
            }
            else
            {
                lblRealName.Content = "Richtiger Name";
                lblRealName.Foreground = Brushes.Black;
                return true;
            }
        }

        // Validierung für Alter
        private void txtAge_LostFocus(object sender, RoutedEventArgs e)
        {
            Test_txtAge();
        }

        private bool Test_txtAge()
        {
            Regex regex = new Regex("^[0-9]*$");
            if (!regex.IsMatch(txtAge.Text))
            {
                lblAge.Content = "Alter *muss eine Zahl sein";
                lblAge.Foreground = Brushes.Red;
                return false;
            }
            else
            {
                lblAge.Content = "Alter";
                lblAge.Foreground = Brushes.Black;
                return true;
            }
        }

        private void rbtnArmorPoints1_Click(object sender, RoutedEventArgs e)
        {
            rbtnSpeedPoints3.IsChecked = true;
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnArmorPoints2_Click(object sender, RoutedEventArgs e)
        {
            rbtnSpeedPoints2.IsChecked = true;
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnArmorPoints3_Click(object sender, RoutedEventArgs e)
        {
            rbtnSpeedPoints1.IsChecked = true;
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnSpeedPoints1_Click(object sender, RoutedEventArgs e)
        {
            rbtnArmorPoints3.IsChecked = true;
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnSpeedPoints2_Click(object sender, RoutedEventArgs e)
        {
            rbtnArmorPoints2.IsChecked = true;
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnSpeedPoints3_Click(object sender, RoutedEventArgs e)
        {
            rbtnArmorPoints1.IsChecked = true;
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void TextChanged(object sender, TextChangedEventArgs e)
        {
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void dpReleaseDate_SelectedDateChanged(object sender, SelectionChangedEventArgs e)
        {
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void cbPosition_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnIsDefaultOperator_Click(object sender, RoutedEventArgs e)
        {
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }

        private void rbtnIsNotDefaultOperator_Click(object sender, RoutedEventArgs e)
        {
            if (state == State.View)
            {
                state = State.Changed;
                AdaptFormToState();
            }
        }
    }
}
