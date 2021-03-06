using M120Projekt.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
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

namespace M120Projekt
{
    /// <summary>
    /// Interaktionslogik für OperatorList.xaml
    /// </summary>
    public partial class OperatorList : UserControl
    {

        List<Operator> operators;

        MainWindow parent;
        public OperatorList(MainWindow parent)
        {
            InitializeComponent();
            this.parent = parent;

            this.operators = Data.Operator.LesenAlle();
            operatorList.ItemsSource = operators;
            operatorList.CanUserAddRows = false;
            operatorList.CanUserSortColumns = true;
            operatorList.IsReadOnly = true;
            operatorList.SelectionMode = DataGridSelectionMode.Single;
            operatorList.AutoGenerateColumns = false;
        }

        private void Row_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            parent.ToRegister((Operator)operatorList.SelectedItem);
        }
    }
}
