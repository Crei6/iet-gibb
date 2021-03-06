using System.Windows;

namespace M120Projekt
{
    /// <summary>
    /// Interaktionslogik für MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            placeholder.Content = new Register(this, null);
        }

        private void NavigateToRegister(object sender, RoutedEventArgs e)
        {
            ToRegister(null);
        }

        public void ToRegister(Data.Operator o)
        {
            placeholder.Content = new Register(this, o);
        }

        private void NavigateToList(object sender, RoutedEventArgs e)
        {
            ToList();
        }

        public void ToList()
        {
            placeholder.Content = new OperatorList(this);
        }
    }
}
