using System.Data.Entity.Migrations;

namespace M120Projekt.Migrations
{
    internal sealed class Configuration : DbMigrationsConfiguration<M120Projekt.Data.Context>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            AutomaticMigrationDataLossAllowed = true;
            ContextKey = "M120Projekt.Data.Context";
        }

        protected override void Seed(M120Projekt.Data.Context context)
        {
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data.
        }
    }
}
