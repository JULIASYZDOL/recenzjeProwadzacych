import Image from 'next/image'
import Link from 'next/link';

export default function Home() {
  return (
    <div className="container">
        <h2><strong>RateProf</strong></h2>
        <input type="text" placeholder="Wyszukaj uczelnię..." />
        <Link href="/dashboard">
            <button>Szukaj!</button>
        </Link>
    </div>
  )
}

