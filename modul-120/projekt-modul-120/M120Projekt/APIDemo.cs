using System;
using System.Diagnostics;

namespace M120Projekt
{
    static class APIDemo
    {
        #region KlasseA
        // Create
        public static void DemoACreate()
        {
            Debug.Print("--- DemoACreate ---");
            // KlasseA
            Data.Operator klasseA1 = new Data.Operator();
            klasseA1.OperatorName = "Fuze";
            klasseA1.RichtigerName = "Alexander Putin";
            klasseA1.Alter = 14;
            klasseA1.Erscheinungsdatum = DateTime.Today;
            klasseA1.Position = "Angreifer";
            klasseA1.Rüstungspunkte = 3;
            klasseA1.Geschwindigkeitspunkte = 1;
            klasseA1.Standardoperator = true;
            Int64 klasseA1Id = klasseA1.Erstellen();
            Debug.Print("Artikel erstellt mit Id:" + klasseA1Id);
        }
        public static void DemoACreateKurz()
        {
            Data.Operator klasseA2 = new Data.Operator { OperatorName = "Artikel 2", RichtigerName = "Fred Müller", Alter = 45, Erscheinungsdatum = DateTime.Today, Position = "Verteidiger", Rüstungspunkte = 2, Geschwindigkeitspunkte = 2, Standardoperator = true };
            Int64 klasseA2Id = klasseA2.Erstellen();
            Debug.Print("Artikel erstellt mit Id:" + klasseA2Id);
        }

        // Read
        public static void DemoARead()
        {
            Debug.Print("--- DemoARead ---");
            // Demo liest alle
            foreach (Data.Operator klasseA in Data.Operator.LesenAlle())
            {
                Debug.Print("Artikel Id:" + klasseA.OperatorId + " Name:" + klasseA.OperatorName);
            }
        }
        // Update
        public static void DemoAUpdate()
        {
            Debug.Print("--- DemoAUpdate ---");
            // KlasseA ändert Attribute
            Data.Operator klasseA1 = Data.Operator.LesenID(1);
            klasseA1.OperatorName = "Artikel 1 nach Update";
            klasseA1.Aktualisieren();
        }
        // Delete
        public static void DemoADelete()
        {
            Debug.Print("--- DemoADelete ---");
            // Data.KlasseA.LesenID(2).Loeschen();
            Debug.Print("Artikel mit Id 2 gelöscht");
        }
        #endregion
    }
}
