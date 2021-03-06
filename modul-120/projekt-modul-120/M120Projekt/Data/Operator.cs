using System;
using System.Collections.Generic;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace M120Projekt.Data
{
    public class Operator
    {
        #region Datenbankschicht
        [Key]
        public Int64 OperatorId { get; set; }
        [Required]
        public String OperatorName { get; set; }
        [Required]
        public String RichtigerName { get; set; }
        [Required]
        public int Alter { get; set; }
        [Required]
        public DateTime Erscheinungsdatum { get; set; }
        [Required]
        public String Position { get; set; }
        [Required]
        public int Rüstungspunkte { get; set; }
        [Required]
        public int Geschwindigkeitspunkte { get; set; }
        [Required]
        public Boolean Standardoperator { get; set; }
        #endregion
        #region Applikationsschicht
        public Operator() { }
        [NotMapped]
        public String BerechnetesAttribut
        {
            get
            {
                return "Im Getter kann Code eingefügt werden für berechnete Attribute";
            }
        }
        public static List<Operator> LesenAlle()
        {
            using (var db = new Context())
            {
                return (from record in db.Operator select record).ToList();
            }
        }
        public static Operator LesenID(Int64 klasseAId)
        {
            using (var db = new Context())
            {
                return (from record in db.Operator where record.OperatorId == klasseAId select record).FirstOrDefault();
            }
        }
        public static List<Operator> LesenAttributGleich(String suchbegriff)
        {
            using (var db = new Context())
            {
                return (from record in db.Operator where record.OperatorName == suchbegriff select record).ToList();
            }
        }
        public static List<Operator> LesenAttributWie(String suchbegriff)
        {
            using (var db = new Context())
            {
                return (from record in db.Operator where record.OperatorName.Contains(suchbegriff) select record).ToList();
            }
        }
        public Int64 Erstellen()
        {
            if (this.Erscheinungsdatum == null) this.Erscheinungsdatum = DateTime.Now;
            using (var db = new Context())
            {
                db.Operator.Add(this);
                db.SaveChanges();
                return this.OperatorId;
            }
        }
        public Int64 Aktualisieren()
        {
            using (var db = new Context())
            {
                db.Entry(this).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
                return this.OperatorId;
            }
        }
        public void Loeschen()
        {
            using (var db = new Context())
            {
                db.Entry(this).State = System.Data.Entity.EntityState.Deleted;
                db.SaveChanges();
            }
        }
        public override string ToString()
        {
            return OperatorId.ToString(); // Für bessere Coded UI Test Erkennung
        }
        #endregion
    }
}
